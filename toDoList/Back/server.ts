const express = require('express');
const mysql = require('mysql2/promise');
const cors = require('cors');
const bodyParser = require('body-parser');

const app = express();
const PORT = 3000;

// Middleware
app.use(cors());
app.use(bodyParser.json());

// Configuración de la base de datos
const dbConfig = {
  host: process.env.DB_HOST || 'localhost',
  user: process.env.DB_USER || 'root',
  password: process.env.DB_PASSWORD || 'daw12',
  database: process.env.DB_NAME || 'taskdb'
};

let connection: any = null;

// Función para conectar a la base de datos con reintentos
async function connectToDatabase(): Promise<void> {
  const maxRetries = 10;
  let retries = 0;

  while (retries < maxRetries) {
    try {
      connection = await mysql.createConnection(dbConfig);
      console.log('✅ Conectado a MySQL');
      
      // Crear tabla si no existe
      await connection!.execute(`
        CREATE TABLE IF NOT EXISTS tasks (
          id INT AUTO_INCREMENT PRIMARY KEY,
          title VARCHAR(255) NOT NULL,
          description TEXT,
          completed BOOLEAN DEFAULT FALSE,
          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )
      `);
      console.log('✅ Tabla tasks verificada/creada');
      return;
    } catch (error) {
      retries++;
      console.log(`⏳ Intento ${retries}/${maxRetries} de conexión a MySQL...`);
      await new Promise(resolve => setTimeout(resolve, 3000));
    }
  }
  
  throw new Error('No se pudo conectar a la base de datos');
}

// Rutas de la API

// GET - Obtener todas las tareas
app.get('/api/tasks', async (req: any, res: any): Promise<void> => {
  try {
    const [rows] = await connection!.execute('SELECT * FROM tasks ORDER BY created_at DESC');
    res.json(rows as any[]);
  } catch (error) {
    const errorMessage = error instanceof Error ? error.message : 'Error desconocido';
    res.status(500).json({ error: errorMessage });
  }
});

// GET - Obtener una tarea por ID
app.get('/api/tasks/:id', async (req: any, res: any): Promise<void> => {
  try {
    const [rows] = await connection!.execute('SELECT * FROM tasks WHERE id = ?', [req.params.id]);
    const rowsArray = rows as any[];
    if (rowsArray.length === 0) {
      res.status(404).json({ error: 'Tarea no encontrada' });
      return;
    }
    res.json(rowsArray[0]);
  } catch (error) {
    const errorMessage = error instanceof Error ? error.message : 'Error desconocido';
    res.status(500).json({ error: errorMessage });
  }
});

// POST - Crear nueva tarea
app.post('/api/tasks', async (req: any, res: any): Promise<void> => {
  try {
    const { title, description } = req.body;
    
    if (!title) {
      res.status(400).json({ error: 'El título es obligatorio' });
      return;
    }

    const [result] = await connection!.execute(
      'INSERT INTO tasks (title, description) VALUES (?, ?)',
      [title, description || '']
    );

    const resultHeader = result as any;
    const [newTask] = await connection!.execute('SELECT * FROM tasks WHERE id = ?', [resultHeader.insertId]);
    const newTaskArray = newTask as any[];
    res.status(201).json(newTaskArray[0]);
  } catch (error) {
    const errorMessage = error instanceof Error ? error.message : 'Error desconocido';
    res.status(500).json({ error: errorMessage });
  }
});

// PUT - Actualizar tarea
app.put('/api/tasks/:id', async (req: any, res: any): Promise<void> => {
  try {
    const { title, description, completed } = req.body;
    
    await connection!.execute(
      'UPDATE tasks SET title = ?, description = ?, completed = ? WHERE id = ?',
      [title, description, completed, req.params.id]
    );

    const [updatedTask] = await connection!.execute('SELECT * FROM tasks WHERE id = ?', [req.params.id]);
    const updatedTaskArray = updatedTask as any[];
    
    if (updatedTaskArray.length === 0) {
      res.status(404).json({ error: 'Tarea no encontrada' });
      return;
    }
    
    res.json(updatedTaskArray[0]);
  } catch (error) {
    const errorMessage = error instanceof Error ? error.message : 'Error desconocido';
    res.status(500).json({ error: errorMessage });
  }
});

// DELETE - Eliminar tarea
app.delete('/api/tasks/:id', async (req: any, res: any): Promise<void> => {
  try {
    const [result] = await connection!.execute('DELETE FROM tasks WHERE id = ?', [req.params.id]);
    const resultHeader = result as any;
    
    if (resultHeader.affectedRows === 0) {
      res.status(404).json({ error: 'Tarea no encontrada' });
      return;
    }
    
    res.json({ message: 'Tarea eliminada correctamente' });
  } catch (error) {
    const errorMessage = error instanceof Error ? error.message : 'Error desconocido';
    res.status(500).json({ error: errorMessage });
  }
});

// Ruta de health check
app.get('/health', (req: any, res: any): void => {
  res.json({ status: 'OK', message: 'Backend funcionando correctamente' });
});

// Iniciar servidor
async function startServer(): Promise<void> {
  try {
    await connectToDatabase();
    app.listen(PORT, '0.0.0.0', () => {
      console.log(`🚀 Servidor ejecutándose en http://localhost:${PORT}`);
    });
  } catch (error) {
    const errorMessage = error instanceof Error ? error.message : 'Error desconocido';
    console.error('❌ Error al iniciar el servidor:', errorMessage);
    process.exit(1);
  }
}

startServer();
