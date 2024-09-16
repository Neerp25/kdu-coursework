import { PayloadAction, createSlice } from "@reduxjs/toolkit";

interface CounterState {
    count: number;
    // inputNumber: number;
}

const initialState: CounterState = 
{
    count: 0,
    // inputNumber: 0
}
const counterSlice = createSlice({
    name: "counter",
    initialState,
    reducers: {
        increment: (state)=>{
            state.count += 1;
            // state.count += 10;
        },
        decrement: (state) => {
            state.count -= 1;
        },
        incrementBy: (state, action: PayloadAction<number>) =>{
            state.count += action.payload;
        },
        decrementBy: (state, action: PayloadAction<number>) =>{
            state.count -= action.payload;
        }
    }
});

export const {increment, decrement, incrementBy,decrementBy} = counterSlice.actions;
export default counterSlice.reducer;