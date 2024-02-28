describe('template spec', () => {
  it('passes', () => {
    cy.visit('http://localhost:5173/')
    cy.get('[data-testid="cypress-title"]').should("exist");
    cy.get('.searchbox').type('Test Search Term');
    cy.get('.searchbox').should('have.value', 'Test Search Term');

      cy.get('.Mid-section').should('exist');
      cy.get('[data-testid="cypress-title-add"]').should('have.text','Add Items');
      cy.get('.Add-itembox').should('have.attr', 'placeholder', 'Add new item');
      cy.get('.submitbtn').should('have.text', 'Submit');
   
 
      const inputText = 'Test Todo';
      cy.get('.Add-itembox').type(inputText).should('have.value', inputText);


      const inputText2 = 'New Todo';
      cy.get('.Add-itembox').type(inputText2);
      cy.get('.submitbtn').click();
      
      cy.get('.submitbtn').click();
      const inputText3 = 'Test Dispatch';
      cy.get('.Add-itembox').type(inputText3);
      cy.get('.submitbtn').click();

      cy.get('.header').should('exist').and('be.visible');
    cy.get('h2[data-testid="cypress-title"]').should('exist').and('be.visible').and('have.text', 'Item Lister');
    cy.get('.searchbox').should('exist').and('be.visible').and('have.attr', 'placeholder', 'Search...');

  });


    

});
 