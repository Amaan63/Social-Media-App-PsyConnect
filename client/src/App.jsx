import { Route, Routes } from "react-router-dom";
import "./App.css";
import Authentication from "./Pages/Authentication/Authentication";
import Message from "./Pages/MessagePage/Message";
import Home from "./Pages/HomePage/Home";

function App() {
  return (
    <>
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/message" element={<Message />} />
        {/* After / anything will come will go to Authentication */}
        <Route path="/*" element={<Authentication />} />
      </Routes>
    </>
  );
}

export default App;
