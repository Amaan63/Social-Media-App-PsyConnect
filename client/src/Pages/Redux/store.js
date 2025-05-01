// redux ke zaroori functions import kar rahe hain
import { applyMiddleware, combineReducers, legacy_createStore } from "redux";

// redux-thunk middleware import kar rahe hain, async actions ke liye
import thunk from "redux-thunk";

// combineReducers se multiple reducers ko ek sath combine karne ka structure banaya gaya hai
const rootReducers = combineReducers({
  // yahan alag-alag reducers ko key ke form mein add kiya ja sakta hai, jaise: user: userReducer
});

// legacy_createStore se store banaya ja raha hai
// applyMiddleware(thunk) ka use async actions (jaise API calls) ke liye kiya jata hai
export const store = legacy_createStore(rootReducers, applyMiddleware(thunk));
