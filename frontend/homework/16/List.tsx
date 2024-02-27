// List.tsx
import { useDispatch, useSelector } from 'react-redux';
import { deleteTodo } from './redux/TodoSlice';
import { selectFilteredTodos } from './redux/selector';

function List() {
  const dispatch = useDispatch();
  const filteredTodos = useSelector(selectFilteredTodos);
  const handleDeleteTodo = (index:number) => {
    dispatch(dispatch(deleteTodo(index)));
  };

  return (
    <div className="todo-container">
      <h2>Items</h2>
      <ul id="todo-list">
        {filteredTodos.length > 0 ? (
          filteredTodos.map((todo, index) => (
            <li key={index} className="todo-item">
              {todo} <button onClick={() => handleDeleteTodo(index)}>X</button>
            </li>
          ))
        ) : (
          <p>No Match found</p>
        )}
      </ul>
    </div>
  );
}

export default List;

