import { useDispatch, useSelector } from "react-redux";
import { DispatchType, RootState } from "./redux/store";
import { decrement, decrementBy, increment, incrementBy } from "./redux/CounterSlice";
import { useEffect, useState } from "react";
import { Products } from "./Products";
import { getProducts } from "./redux/thunks/getProducts";
import { Dispatch } from "@reduxjs/toolkit";
import SnackbarSlice, { hideSnackbar, setMessage, showSnackbar } from "./redux/SnackbarSlice";
import  { selectSnackbar } from "./redux/SnackbarSlice";
import Heading from "./HeadingComponent";

function App() {
  const dispatch = useDispatch<DispatchType>();

  useEffect(() => {
    
    dispatch(getProducts());
  }, []); 
  

  const statev = useSelector((state:RootState)=>state.products.state)

   console.log(statev)
   if(statev==="error"){
    dispatch(showSnackbar("UnSucessfull"));

   }else{
    dispatch(showSnackbar("Sucessfull"));
   }
    
  const snackbar = useSelector(selectSnackbar);
  
  return (
    <div id  = "main">
      <Heading/>
      <Products/>
      <div id="snackbox" style={{ backgroundColor: statev === "error" ? "red" : "green", color: "white", textAlign: "center" }}>
        {snackbar.show && <span>{snackbar.message}</span>}
      </div>
      </div>
    
  );
}

export default App;

