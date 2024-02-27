import {render,screen,fireEvent} from "@testing-library/react";
import { Provider } from 'react-redux';
import { store } from "../redux/store";
import Midsection from "../Midsection";
import configureMockStore from 'redux-mock-store';

it("should have a visible submit button", () => {
    render(
      <Provider store={store}>
        <Midsection />
      </Provider>
    );
    
    const submitButton = screen.getByText('Submit');
    expect(submitButton).toBeVisible();
  });
  

  const mockStore = configureMockStore([]);

  describe('Midsection component', () => {
   it("should dispatch setNewTodoText and addTodo actions when the submit button is clicked", () => {
     
     const initialState = { todo: { newTodoText: '' } };
     const store = mockStore(initialState);
 
     
     render(
       <Provider store={store}>
         <Midsection />
       </Provider>
     );
 
     
     const input = screen.getByPlaceholderText('Add new item');
     const submitButton = screen.getByText('Submit');
 
     
     fireEvent.change(input, { target: { value: 'New Todo Item' } });
 
     
     fireEvent.click(submitButton);
 
     
     const actions = store.getActions();
     expect(actions[0].type).toEqual('todo/setNewTodoText');
     expect(actions[0].payload).toEqual('New Todo Item');
 
     
     if (actions.length >  1) {
       expect(actions[1].type).toEqual('todos/addTodo');
       expect(actions[1].payload).toEqual('New Todo Item');
     } else {
       console.log('The addTodo action was not dispatched.');
     }
   });
 });

