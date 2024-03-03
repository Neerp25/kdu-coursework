import {render,screen} from "@testing-library/react";
import App from "../App";
import { Provider } from 'react-redux';
import { store } from "../redux/store";

describe('App integration test', () => {
  it('renders Todolist component and checks basic functionality', () => {
    render(
            <Provider store={store}>
            <App />
        </Provider>);
    
    const itemlisterElement = screen.getByText(/Item Lister/i);
    expect(itemlisterElement).toBeInTheDocument();

    const additemElement = screen.getByText(/Add Items/i);
    expect(additemElement).toBeInTheDocument();
        
  });
});

