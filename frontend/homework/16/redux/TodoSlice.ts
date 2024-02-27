import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface TodoState {
  searchTerm: string;
  todos: string[];
  newTodoText: string;
}

const initialState: TodoState = {
  searchTerm: '',
  todos: [],
  newTodoText: '',
};


const todoSlice = createSlice({
  name: 'todo',
  initialState,
  reducers: {
    setSearchTerm: (state, action: PayloadAction<string>) => {
      state.searchTerm = action.payload;
    },
    setTodos: (state, action: PayloadAction<string[]>) => {
      state.todos = action.payload;
    },
    setNewTodoText: (state, action: PayloadAction<string>) => {
      state.newTodoText = action.payload;
    },
    addTodo: (state, action: PayloadAction<string>) => {
      state.todos.push(action.payload);
    },
    deleteTodo: (state, action: PayloadAction<number>) => {
      state.todos.splice(action.payload,  1);
    },
    
  },
});

export const { setSearchTerm, setTodos, setNewTodoText,addTodo,deleteTodo } = todoSlice.actions;
export default todoSlice.reducer;

