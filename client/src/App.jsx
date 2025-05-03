import { Route, Routes } from "react-router-dom";
import "./App.css";
import Authentication from "./Pages/Authentication/Authentication";
import Message from "./Pages/MessagePage/Message";
import Home from "./Pages/HomePage/Home";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { getUserProfileAction } from "./Redux/Authentication/authentication.action";

function App() {
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const { auth } = useSelector((store) => store);
  useEffect(() => {
    dispatch(getUserProfileAction(jwt));
  }, [jwt]);
  return (
    <>
      <Routes>
        <Route path="/*" element={auth.user ? <Home /> : <Authentication />} />
        {/* <Route path="/message" element={<Message />} /> */}
        {/* After / anything will come will go to Authentication */}
        <Route path="/*" element={<Authentication />} />
      </Routes>
    </>
  );
}

export default App;
