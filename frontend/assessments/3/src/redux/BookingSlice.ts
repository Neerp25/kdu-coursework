
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { RoomType, AddOn } from './Roomtypes';

interface BookingState {
 roomTypes: RoomType[];
 selectedRoomType: RoomType | null;
 startDate: string | null;
 endDate: string | null;
 selectedAddOns: AddOn[];
 cost: number | null;
}

const initialState: BookingState = {
 roomTypes: [],
 selectedRoomType: null,
 startDate: null,
 endDate: null,
 selectedAddOns: [],
 cost: null,
};

export const bookingSlice = createSlice({
 name: 'booking',
 initialState,
 reducers: {
    setRoomTypes: (state, action: PayloadAction<RoomType[]>) => {
      state.roomTypes = action.payload;
    },
    setSelectedRoomType: (state, action: PayloadAction<RoomType | null>) => {
      state.selectedRoomType = action.payload;
    },
    setStartDate: (state, action: PayloadAction<string | null>) => {
      state.startDate = action.payload;
    },
    setEndDate: (state, action: PayloadAction<string | null>) => {
      state.endDate = action.payload;
    },
    setSelectedAddOns: (state, action: PayloadAction<AddOn[]>) => {
      state.selectedAddOns = action.payload;
    },
 },
});

export const { setRoomTypes, setSelectedRoomType, setStartDate, setEndDate, setSelectedAddOns } = bookingSlice.actions;
export default bookingSlice.reducer;

