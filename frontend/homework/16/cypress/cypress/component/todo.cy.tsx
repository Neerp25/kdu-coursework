import { Provider } from 'react-redux'
import Header from '../../src/Header'
import { store } from '../../src/redux/store'

describe('<Header />', () => {
  it('renders', () => {
    cy.viewport(1076,876)
    cy.mount(<Provider store={store}><Header /></Provider>)
    cy.get('.header').should('exist').and('be.visible');
    cy.get('h2').should('exist').and('be.visible').and('have.text', 'Item Lister');
    cy.get('.searchbox').should('exist').and('be.visible').and('have.text', '');
  })
});

