// array of shoe object
const shoes = [
    { type: 'Running Shoes', color: 'Red', size: 9, price: 50 },
    { type: 'Casual Shoes', color: 'Black', size: 10, price: 60 }
  ];
// array of shirt object
  const shirts = [
    { type: 'T-Shirt', color: 'Blue', size: 'M', price: 25 },
    { type: 'Dress Shirt', color: 'White', size: 'L', price: 40 },
    { type: 'Polo Shirt', color: 'Blue', size: 'S', price: 35 }
  ];
  

  const warehouse = [...shoes, ...shirts];
  
  let totalWorth = 0;
  for (const product of warehouse) {
    totalWorth += product.price;
  }
  
 
  warehouse.sort((a, b) => b.price - a.price);
  
  
  const blueProducts = warehouse.filter(product => product.color === 'Blue');
  
  console.log('Warehouse:', warehouse);
  console.log('Total Worth of Products:', totalWorth);
  console.log('Warehouse Products with Blue Color:', blueProducts);