import React, { useContext } from 'react'
import CloseSharpIcon from '@mui/icons-material/CloseSharp';
import { TodoFilterContext } from './Midsection';

function List() {
  const {filteredTodos,handleDeleteTodo} = useContext(TodoFilterContext);
  return (
    <div className="todo-container">
      <h2>Items</h2>
      <ul id="todo-list">
      {filteredTodos.length > 0 ? (
        filteredTodos.map((todo, index) => (
          <li key={index} className="todo-item">
            {todo} <button onClick={() => handleDeleteTodo(index)}><CloseSharpIcon/></button>
          </li>
        ))
      ) : (
        
        <p>No Match found</p>
      )}
    </ul>
    </div>
  )
}

export default List