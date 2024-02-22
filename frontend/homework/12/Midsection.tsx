import React, { createContext, useContext, useState, useEffect } from 'react';
import List from './List';
import { TodoContext } from './Todolist';

interface ITodoFilterContext {
  filteredTodos: string[];
  setFilteredTodos: any;
  handleDeleteTodo: (index: number) => void;
}

export const TodoFilterContext = createContext<ITodoFilterContext>({
  filteredTodos: [],
  setFilteredTodos: () => {},
  handleDeleteTodo: () => {},
});
interface TodoFilterProviderProps {
  children: React.ReactNode;
}

export function TodoFilterProvider({children}:TodoFilterProviderProps) {
  const [filteredTodos, setFilteredTodos] = useState<string[]>([]);
  const { searchTerm, todos, setTodos } = useContext(TodoContext);

  const handleDeleteTodo = (index: number) => {
    const updatedTodos = [...todos];
    updatedTodos.splice(index, 1);
    setTodos(updatedTodos);
  };

  useEffect(() => {
    setFilteredTodos(
      searchTerm
        ? todos.filter((todo) => todo.toLowerCase().includes(searchTerm.toLowerCase()))
        : todos
    );
  }, [searchTerm, todos]);

  return (
    <TodoFilterContext.Provider value={{ filteredTodos, setFilteredTodos, handleDeleteTodo }}>
      {children}
    </TodoFilterContext.Provider>
  );
};

const Midsection = () => {
  const { newTodoText, setNewTodoText, todos, setTodos } = useContext(TodoContext);

  const handleAddTodo = () => {
    if (newTodoText.trim() !== '') {
      setTodos([...todos, newTodoText]);
      setNewTodoText('');
    }
  };

  return (
    <div id="Mid-section">
      <h2>Add Items</h2>
      <input
        id="Add-itembox"
        type="text"
        placeholder="Add new item"
        value={newTodoText}
        onChange={(e) => setNewTodoText(e.target.value)}
      />
      <button id="submitbtn" onClick={handleAddTodo}>
        Submit
      </button>
      <TodoFilterProvider>
        <List />
      </TodoFilterProvider>
    </div>
  );
};

export default Midsection;
