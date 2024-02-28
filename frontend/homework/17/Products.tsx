import { useSelector } from "react-redux";
import { RootState } from "./redux/store";
import { ProductInfo } from "./redux/ProductInfo";
import "./Product.scss";
export function Products(){
    const products= useSelector((state:RootState)=>state.products.products);
    const loadingState= useSelector((state:RootState)=>state.products.state);
    const productsError= useSelector((state:RootState)=>state.products.error);
    
    if(loadingState==="pending"){
        return<div>
          <div id="loader"></div>
        </div>
    }
    if(loadingState==="error"){
        return <div>Error {productsError}</div>
    }
    

    return (
      <div className="product-list">
      {products.map((product) => (
        <div key={product.id} className="product">
         
            <img
              src={product.image}
              alt={product.title}
              style={{ height: '150px', width: '150px' }}
            />
          <p>{product.title}</p>
          <p>Price: ${product.price}</p>
        </div>
      ))}
    </div>
      );


}