import {render,screen,fireEvent} from "@testing-library/react";
import { Provider } from 'react-redux';
import configureMockStore from 'redux-mock-store';
import List from "../List";
import { store } from "../redux/store";

const mockStore = configureMockStore([]);

it('dispatches deleteTodo action when the delete button is clicked', () => {   
    const initialState = { todo: { todos: ['Todo   1', 'Todo   2'] } };
    const store = mockStore(initialState);   
    render(
      <Provider store={store}>
        <List />
      </Provider>
    );   
    const deleteButton = screen.getAllByText('X')[0];   
    fireEvent.click(deleteButton);
    const actions = store.getActions();
    expect(actions[0].type).toEqual('todo/deleteTodo'); 
    expect(actions[0].payload).toEqual(0); 
  });

    it("should have 'Items' in the List", () => {
     render(
            <Provider store={store}>
                <List />
            </Provider>
            );
        
            const listText = screen.getByText('Items');
            expect(listText).toBeVisible();
        });


