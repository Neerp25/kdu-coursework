import React from 'react';

import { ProductInfo } from './ProductInfo';

import { Link } from 'react-router-dom';

interface ProductDetailsProps {
  product: ProductInfo;
}

const ProductDetails: React.FC<ProductDetailsProps> = ({ product }) => {
  const productDetailStyle: React.CSSProperties = {
    display: 'flex',
    boxSizing: 'border-box',
    padding: '20px',
    width: '60%',
    margin: 'auto',
    backgroundColor: '#fff',
    border: '1px solid #ddd',
    borderRadius: '5px',
    boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)',
  };

  const imageStyle = {
    width: '250px',
    height: '250px',
    borderRadius: '5px',
    marginRight: '20px',
    marginTop: '20px',
  };

  const contentStyle = {
    flex: 1,
  };

  const titleStyle = {
    color: '#2a2a72',
    marginBottom: '10px',
  };

  const paragraphStyle = {
    marginBottom: '10px',
    color: '#666',
  };

  const priceStyle = {
    fontWeight: 'bold',
    color: '#f00',
  };

  const categoryStyle = {
    fontStyle: 'italic',
  };

  const ratingStyle = {
    fontWeight: 'bold',
    color: '#4CAF50',
  };

  return (
    <div id="Product-detail" style={productDetailStyle}>
      <img src={product.image} alt={product.title} style={imageStyle} />
      <div style={contentStyle}>
        <h1 style={titleStyle}>{product.title}</h1>
        <p style={paragraphStyle}>{product.description}</p>
        <p style={paragraphStyle}>Price: <span style={priceStyle}>${product.price}</span></p>
        <p style={paragraphStyle}>Category: <span style={categoryStyle}>{product.category}</span></p>
        <p style={paragraphStyle}>Rating: <span style={ratingStyle}>{product.rating.rate} ({product.rating.count} reviews)</span></p>
        <Link to="/" style={{ textDecoration: 'none' }}>
          <button style={{ padding: '10px 20px', backgroundColor: '#2a2a72', color: '#fff', border: 'none', borderRadius: '5px', cursor: 'pointer' }}>
            Back to Main
          </button>
        </Link>
      </div>
    </div>
  );
};

export default ProductDetails;



