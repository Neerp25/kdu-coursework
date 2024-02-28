import { createSlice,PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "./store";

interface SnackbarState {
    show:boolean,
    message:string
  }

  const initialState:SnackbarState={
    show:false,
    message:""
  }

  export const SnackbarSlice = createSlice({
    name: "snackbar",
    initialState,  
    reducers: {
         setMessage: (state,action:PayloadAction<string>)=>{
            state.message=action.payload
            state.show = true;
        },
        showSnackbar: (state, action: PayloadAction<string>) => {
            state.show = true;
            state.message = action.payload;
          },
        hideSnackbar: (state) => {
            state.show = false;
            state.message = "";
          },
    },
  });

  export const {setMessage,showSnackbar,hideSnackbar} = SnackbarSlice.actions;
  export const selectSnackbar = (state: RootState) => state.snackbar;
  export default SnackbarSlice.reducer;