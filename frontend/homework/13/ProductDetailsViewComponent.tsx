import React, { useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { ProductInfo } from './ProductInfo';
import ProductDetails from './ProductDetailsComponent';

interface ProductDetailsViewComponentProps {
  allProductinfo: ProductInfo[];
  setShowProductList: React.Dispatch<React.SetStateAction<boolean>>;
}

const ProductDetailsViewComponent: React.FC<ProductDetailsViewComponentProps> = ({
  allProductinfo,
  setShowProductList,
}) => {
  let { id } = useParams();
  const product = allProductinfo.find((p) => p.id.toString() === id);

  useEffect(() => {
    setShowProductList(false);
    return () => setShowProductList(true);
  }, [setShowProductList]);

  return product ? <ProductDetails product={product} /> : <div>Product not found</div>;
};

export default ProductDetailsViewComponent;