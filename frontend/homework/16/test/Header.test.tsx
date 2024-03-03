import {render,screen} from "@testing-library/react";
import { Provider } from 'react-redux';
import { store } from "../redux/store";

import Header from "../Header";

it("should have 'Item Lister' in the header", () => {
    render(
      <Provider store={store}>
        <Header />
      </Provider>
    );
  
    const headerText = screen.getByText('Item Lister');
    expect(headerText).toBeVisible();
  });

  it("should have a visible search box", () => {
    render(
      <Provider store={store}>
        <Header />
      </Provider>
    );
  
    const searchBox = screen.getByPlaceholderText('Search...');
    expect(searchBox).toBeVisible();
  });
