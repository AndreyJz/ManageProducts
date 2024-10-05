import { useState } from 'react'
import './App.css'
import List from "./components/List.tsx";


function App() {

    const [showApp, setShowApp] = useState<boolean>(true);
    const [entity, setEntity] = useState<string>("")

    const handleClick = (name : string) => {
        setShowApp(false);
        setEntity(name);
    }

  return (
    <>
        {showApp ? (
            <div className="container">
                <h1 className={"welcome"}>Welcome</h1>
                <div className="button-container">
                    <button onClick={() => handleClick("Category")} className={"btn"}>Category</button>
                    <button onClick={() => handleClick("Customer")} className={"btn"}>Customer</button>
                    <button onClick={() => handleClick("Product")} className={"btn"}>Product</button>
                    <button onClick={() => handleClick("Purchase")} className={"btn"}>Purchase</button>
                    <button onClick={() => handleClick("PurchaseProduct")} className={"btn"}>Purchase-Product</button>
                </div>
            </div>)
            : <List name={entity}/> }
    </>
  )
}

export default App
