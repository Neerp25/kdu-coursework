import React, { useState } from 'react';

import Header from './Header';
import Midsection from './Midsection';

interface ITodoContext{
    searchTerm:string;
    setSearchTerm:any;
    todos:string[];
    setTodos:any;
    newTodoText:string;
    setNewTodoText:any;
}

export const TodoContext = React.createContext<ITodoContext>({
    searchTerm:'',
    setSearchTerm:'',
    todos:[],
    setTodos:[],
    newTodoText:'',
    setNewTodoText:'',
}
    
);

interface TodoProviderProps {
    children: React.ReactNode;
  }

export function TodoProvider({ children }:TodoProviderProps){
    const [searchTerm, setSearchTerm] = useState<string>('');
    const [todos, setTodos] = useState<string[]>([]);
    const [newTodoText, setNewTodoText] = useState<string>('');

return (
    <TodoContext.Provider value={{ searchTerm, todos, newTodoText,setNewTodoText,setTodos ,setSearchTerm}} >
        {children}
    </TodoContext.Provider>
);
};

function Todolist() {

  return (
    <div>
      <TodoProvider>
            <Header/>
            <Midsection/>
        </TodoProvider>
    </div>
  );
}

export default Todolist;