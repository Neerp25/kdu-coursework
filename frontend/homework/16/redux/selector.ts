import { RootState } from './store'; 

export const selectFilteredTodos = (state: RootState) => {
  const { todos, searchTerm } = state.todo;
  return searchTerm
    ? todos.filter((todo) => todo.toLowerCase().includes(searchTerm.toLowerCase()))
    : todos;
};