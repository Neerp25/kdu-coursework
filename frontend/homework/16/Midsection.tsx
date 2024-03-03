// Midsection.tsx
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from './redux/store';
import List from './List';
import { addTodo, setNewTodoText } from './redux/TodoSlice';


const Midsection = () => {
  const dispatch = useDispatch();
  const newTodoText = useSelector((state: RootState) => state.todo.newTodoText);

  const handleAddTodo = () => {
    if (newTodoText.trim() !== '') {
      dispatch(addTodo(newTodoText));
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
        onChange={(e) => dispatch(setNewTodoText(e.target.value))}
      />
      <button id="submitbtn" onClick={handleAddTodo}>
        Submit
      </button>
      <List />
    </div>
  );
};

export default Midsection;
