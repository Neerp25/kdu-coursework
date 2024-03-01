import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { setRoomTypes, setSelectedRoomType, setStartDate, setEndDate, setSelectedAddOns } from './redux/BookingSlice';
import { RoomType, AddOn } from './redux/Roomtypes';
import { fetchRoomTypesAndAddOns } from './redux/FetchRoomtypes';
import { RootState } from './redux/Roomtypes';
import "./Bookingform.scss";

const BookingForm: React.FC = () => {
 const dispatch = useDispatch();
 const state = useSelector((state: RootState) => state); 
 const { roomTypes, selectedRoomType, startDate, endDate, selectedAddOns } = state.booking;
 const [isFormValid, setIsFormValid] = useState(false);
 useEffect(() => {
    const fetchData = async () => {
      const data = await fetchRoomTypesAndAddOns();
      dispatch(setRoomTypes(data.roomTypes));
    };

    fetchData();
 }, [dispatch]);

 useEffect(() => {
    const isValid = selectedRoomType !== null && startDate !== null && endDate !== null;
    setIsFormValid(isValid);
}, [selectedRoomType, startDate, endDate]);

 const handleRoomTypeChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedRoomTypeId = e.target.value;
    const selectedRoomType = roomTypes.find((type:RoomType) => type.id.toString() === selectedRoomTypeId);
    if (selectedRoomType) {
      dispatch(setSelectedRoomType(selectedRoomType));
    }
 };

 const handleStartDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(setStartDate(e.target.value));
 };

 const handleEndDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(setEndDate(e.target.value));
 };


 const handleAddOnsChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedAddOnNames = Array.from(e.target.selectedOptions, option => option.value); 
    const selectedAddOns: AddOn[] = selectedRoomType?.addOns.filter((addOn:AddOn) => selectedAddOnNames.includes(addOn.name)) || [];  
    dispatch(setSelectedAddOns(selectedAddOns));
};

const calculateTotalCost = (): number => {
    if (!selectedRoomType || !startDate || !endDate) {
       return 0; 
    }
    const start = new Date(startDate);
    const end = new Date(endDate);
    const days = Math.ceil((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24));
    let totalCost = selectedRoomType.costPerNight * days;  
    selectedAddOns.forEach(addOn => {
       totalCost += addOn.cost * days;
    });  
    const tax = totalCost * 0.18;
    totalCost += tax;  
    return totalCost;
   };
   

 return (
    <div className="booking-container">
    <h2 className="centered-heading">Hotel Booking</h2>
    <div className="booking-section">
    <div className="heading-container">
      <h3 className="section-heading">Select Room Type</h3>
    </div>
    <select
      value={selectedRoomType?.id || ''}
      onChange={handleRoomTypeChange}
      className="booking-select"
    >
      <option value="">Select Room Type</option>
      {roomTypes.map((type: RoomType) => (
        <option key={type.id} value={type.id.toString()}>{type.name}</option>
      ))}
    </select>
 </div>
 <div className="booking-section">
    <div className="heading-container">
      <h3 className="section-heading">Select Date</h3>
    </div>
    <div className='date-section'>
    <input
      type="date"
      value={startDate || ''}
      onChange={handleStartDateChange}
      className="booking-input"
    />
    <input
      type="date"
      value={endDate || ''}
      onChange={handleEndDateChange}
      className="booking-input"
    />
    </div>
   
 </div>
 <div className="booking-section">
    <div className="heading-container">
      <h3 className="section-heading">Select Additional Add-ons/Preferences</h3>
    </div>
    <select
      multiple
      value={selectedAddOns.map((addOn: AddOn) => addOn.name)}
      onChange={handleAddOnsChange}
      className="booking-select"
    >
      {selectedRoomType?.addOns.map((addOn: AddOn) => (
        <option key={addOn.name} value={addOn.name}>{addOn.name}</option>
      ))}
    </select>
 </div>
 <div className="booking-section">
    <p className="total-cost">Total Cost: {calculateTotalCost().toFixed(2)} USD</p>
    <button type="button" disabled={!isFormValid} className="submit-button">Submit</button>
 </div>
</div>
   
 );
};

export default BookingForm;

