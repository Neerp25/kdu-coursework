export interface AddOn {
    name: string;
    cost: number;
    currency: string;
   }
   
export interface RoomType {
    id: number;
    name: string;
    costPerNight: number;
    currency: string;
    addOns: AddOn[];
   }

export interface RootState {
    booking: {
       roomTypes: RoomType[];
       selectedRoomType: RoomType | null;
       startDate: string | null;
       endDate: string | null;
       selectedAddOns: AddOn[];
       cost: number | null;
    };
}
   
export  interface RoomTypesResponse {
    roomTypes: RoomType[];
   }
   

   