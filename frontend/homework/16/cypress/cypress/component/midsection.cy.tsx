
import { mount } from 'cypress/react18';
import { Provider } from 'react-redux';
import { store } from '../../src/redux/store';
import Midsection from '../../src/Midsection';


describe('<Midsection />', () => {
  it('renders', () => {
    cy.viewport(1076, 876);

    mount(
      <Provider store={store}>
        <Midsection />
      </Provider>
    );

    cy.get('.Mid-section').should('exist').and('be.visible');
    cy.get('.mid-title').should('exist').and('be.visible').and('have.text', 'Add Items');
    cy.get('.Add-itembox').should('exist').and('be.visible').and('have.value', '');
    cy.get('.submitbtn').should('exist').and('be.visible').and('have.text', 'Submit');
    
  });


});
