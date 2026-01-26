import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { Task, TaskService } from './app.service';

@Component({
  selector: 'app-root',
  imports: [ CommonModule, FormsModule, DatePipe],
  templateUrl: './app.html',
  styleUrls: ['./app.css'],
})
export class AppComponent implements OnInit {
  tasks: Task[] = [];
  currentTask: Task = { title: '', description: '', completed: false };
  editingTask: boolean = false;

  constructor(private taskService: TaskService) {}

  ngOnInit(): void {
    this.loadTasks();
  }

  loadTasks(): void {
    this.taskService.getTasks().subscribe({
      next: (data: Task[]) => {
        this.tasks = data;
      },
      error: (error: any) => {
        console.error('Error al cargar tareas:', error);
        alert('Error al conectar con el backend. Verifica que esté ejecutándose.');
      }
    });
  }

  saveTask(): void {
    if (!this.currentTask.title.trim()) {
      alert('El título es obligatorio');
      return;
    }

    if (this.editingTask && this.currentTask.id) {
      this.taskService.updateTask(this.currentTask.id, this.currentTask).subscribe({
        next: () => {
          this.loadTasks();
          this.resetForm();
        },
        error: (error: any) => console.error('Error al actualizar:', error)
      });
    } else {
      this.taskService.createTask(this.currentTask).subscribe({
        next: () => {
          this.loadTasks();
          this.resetForm();
        },
        error: (error: any) => console.error('Error al crear:', error)
      });
    }
  }

  editTask(task: Task): void {
    this.currentTask = { ...task };
    this.editingTask = true;
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  deleteTask(id: number): void {
    if (confirm('¿Estás seguro de eliminar esta tarea?')) {
      this.taskService.deleteTask(id).subscribe({
        next: () => this.loadTasks(),
        error: (error: any) => console.error('Error al eliminar:', error)
      });
    }
  }

  toggleComplete(task: Task): void {
    const updatedTask = { ...task, completed: !task.completed };
    this.taskService.updateTask(task.id!, updatedTask).subscribe({
      next: () => this.loadTasks(),
      error: (error: any) => console.error('Error al actualizar:', error)
    });
  }

  cancelEdit(): void {
    this.resetForm();
  }

  resetForm(): void {
    this.currentTask = { title: '', description: '', completed: false };
    this.editingTask = false;
  }
}

export { AppComponent as App };
