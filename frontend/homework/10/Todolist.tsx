import React, { useState } from 'react';

import Header from './Header';
import Midsection from './Midsection';

function Todolist() {

  const [searchTerm, setSearchTerm] = useState<string>('');
  const [todos, setTodos] = useState<string[]>([]);
  
  const [newTodoText, setNewTodoText] = useState<string>('');

  return (
    <div>
      <Header searchTerm={searchTerm} setSearchTerm={setSearchTerm}/>
      <Midsection
        searchTerm={searchTerm}
        setSearchTerm={setSearchTerm}
        newTodoText={newTodoText}
        setNewTodoText={setNewTodoText}
        todos={todos}
        setTodos={setTodos}
      />
    </div>
  );
}

export default Todolist;

