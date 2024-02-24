import React from 'react';
import { Link } from 'react-router-dom';
import { ProductInfo } from './ProductInfo';

interface ProductListProps {
  products: ProductInfo[];
}

const ProductList: React.FC<ProductListProps> = ({ products }) => {
  return (
    <div className="product-list">
      {products.map((product) => (
        <div key={product.id} className="product">
          <Link to={`/product/${product.id}`}>
            <img
              src={product.image}
              alt={product.title}
              style={{ height: '150px', width: '150px' }}
            />
          </Link>
          <p>{product.title}</p>
          <p>Price: ${product.price}</p>
        </div>
      ))}
    </div>
  );
};

export default ProductList;