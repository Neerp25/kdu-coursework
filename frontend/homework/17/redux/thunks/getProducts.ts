import { createAsyncThunk } from "@reduxjs/toolkit";
import { useDispatch, useSelector } from "react-redux";
import { DispatchType } from "../store";
import { showSnackbar } from "../SnackbarSlice";
// import { DispatchType, RootState } from "redux/store";

export const getProducts = createAsyncThunk("getProducts", async()=>{
 try{
   const response = await fetch("https://fakestoreapi.com/products");
   const data = await response.json();

   return data;
 }catch(error){
    // const dispatch = useDispatch<DispatchType>();
    // dispatch(showSnackbar("USucessfull"));
    // return "Error while making API call";
    // console.log("1101")
    throw error;
    
 }
});

