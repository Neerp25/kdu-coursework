
import { RoomTypesResponse } from './Roomtypes';
export const fetchRoomTypesAndAddOns = async (): Promise<RoomTypesResponse> => {
 try {
    const response = await fetch('https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json');
    if (!response.ok) {
      throw new Error('Network response Failed!');
    }
    const data: RoomTypesResponse = await response.json();
    return data;
 } catch (error) {
    console.error('Fetch operation Failed:', error);
    return { roomTypes: [] };
 }
};

