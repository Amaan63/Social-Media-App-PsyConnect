import { Route, Routes } from "react-router-dom";
import "./App.css";
import Authentication from "./Pages/Authentication/Authentication";
import Message from "./Pages/MessagePage/Message";
import Home from "./Pages/HomePage/Home";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { getUserProfileAction } from "./Redux/Authentication/authentication.action";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function App() {
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const { auth } = useSelector((store) => store.auth);

  useEffect(() => {
    // Check if there's no user data AND the profile isn't already being fetched
    if (!auth?.user && !auth?.loading) {
      dispatch(getUserProfileAction(jwt));
    }
  }, [jwt, auth?.user, auth?.loading, dispatch]);

  return (
    <>
      <Routes>
        <Route path="/home/*" element={<Home />} />
        <Route path="/message" element={<Message />} />
        {/* After / anything will come will go to Authentication */}
        <Route path="/*" element={<Authentication />} />
      </Routes>
      <ToastContainer position="top-center" autoClose={3000} />
    </>
  );
}

export default App;
