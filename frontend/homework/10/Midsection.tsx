import React from 'react';
import List from './List';

interface MidsectionProps {
  searchTerm: string;
  setSearchTerm: (value: string) => void;
  newTodoText: string;
  setNewTodoText: (value: string) => void;
  todos: string[];
  setTodos: (value: string[]) => void;
}

function Midsection({
  searchTerm,
  setSearchTerm,
  newTodoText,
  setNewTodoText,
  todos,
  setTodos,
}: MidsectionProps) {
  const handleAddTodo = () => {
    if (newTodoText.trim() !== '') {
      setTodos([...todos, newTodoText]);
      setNewTodoText('');
    }
  };

  const handleDeleteTodo = (index: number) => {
    const updatedTodos = [...todos];
    updatedTodos.splice(index, 1);
    setTodos(updatedTodos);
  };

  const filteredTodos = searchTerm
    ? todos.filter((todo) => todo.toLowerCase().includes(searchTerm.toLowerCase()))
    : todos;

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
      <List todos={filteredTodos} onDelete={handleDeleteTodo} />
    </div>
  );
}

export default Midsection;
