import './App.css'
import { Provider } from 'react-redux'
import store from './redux/store'
import BookingForm from './Bookingform'

function App() {
  
  return (
    <>     
      <Provider store={store}>
      <div>
        <BookingForm />
      </div>
    </Provider>
    </>
  )
}

export default App
