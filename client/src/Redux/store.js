// redux ke zaroori functions import kar rahe hain
import { applyMiddleware, combineReducers, legacy_createStore } from "redux";

// redux-thunk middleware import kar rahe hain, async actions ke liye
import { thunk } from "redux-thunk"; // ✅ named import

import { authenticationReducer } from "./Authentication/authentication.reducer";
import { postReducer } from "./Post/post.reducer";
import { messageReducer } from "./Message/message.reducer";

// combineReducers se multiple reducers ko ek sath combine karne ka structure banaya gaya hai
const rootReducers = combineReducers({
  auth: authenticationReducer,
  post: postReducer,
  message: messageReducer,
});

// legacy_createStore se store banaya ja raha hai
// applyMiddleware(thunk) ka use async actions (jaise API calls) ke liye kiya jata hai
export const store = legacy_createStore(rootReducers, applyMiddleware(thunk)); // ✅ this now works
