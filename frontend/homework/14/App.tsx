import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ScrollToTop from './ScrollToTop'
import FormPage from './FormPage'
import Timer from './Timer'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    
     <ScrollToTop/>
     <FormPage/>
     <Timer/>
    </>
  )
}

export default App
