import axios from "axios";
import { api, API_BASE_URL } from "../../config/api";
import {
  GET_PROFILE_FAILURE,
  GET_PROFILE_REQUEST,
  GET_PROFILE_SUCCESS,
  LOGIN_FAILURE,
  LOGIN_REQUEST,
  LOGIN_SUCCESS,
  REGISTER_FAILURE,
  REGISTER_REQUEST,
  REGISTER_SUCCESS,
  SEARCH_USER_FAILURE,
  SEARCH_USER_REQUEST,
  SEARCH_USER_SUCCESS,
  UPDATE_PROFILE_FAILURE,
  UPDATE_PROFILE_REQUEST,
  UPDATE_PROFILE_SUCCESS,
} from "./authentication.actionType";

{
  /* This is the login action creator - it returns a function that takes 'dispatch' as argument */
}
export const loginUserAction = (loginData) => async (dispatch) => {
  dispatch({ type: LOGIN_REQUEST }); // 🚀 Dispatch action to show loading state (e.g., spinner)

  try {
    const { data } = await axios.post(
      `${API_BASE_URL}/public/auth/signin`, // 🔗 API endpoint for login
      loginData.data // 📦 Send login credentials (email & password)
    );

    if (data.token) {
      localStorage.setItem("jwt", data.token); // 💾 Save JWT token to localStorage
    }

    console.log("Login Successfull", data);

    dispatch({ type: LOGIN_SUCCESS, payload: data.token }); // ✅ Dispatch success action with token
  } catch (error) {
    console.log("Error -----------", error); // 🐞 Log any error for debugging
    dispatch({ type: LOGIN_FAILURE, payload: error }); // ❌ Dispatch failure action with error
  }
};

export const registerUserAction = (loginData) => async (dispatch) => {
  dispatch({ type: REGISTER_REQUEST });

  try {
    const { data } = await axios.post(
      `${API_BASE_URL}/public/auth/signup`,
      loginData.data
    );

    if (data.token) {
      localStorage.setItem("jwt", data.token);
    }

    console.log("Register Successfull", data);

    dispatch({ type: REGISTER_SUCCESS, payload: data.token });
  } catch (error) {
    console.log("Error -----------", error);
    dispatch({ type: REGISTER_FAILURE, payload: error });
  }
};

export const getUserProfileAction = (jwt) => async (dispatch) => {
  dispatch({ type: GET_PROFILE_REQUEST });

  try {
    const { data } = await axios.get(`${API_BASE_URL}/private/user/profile`, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });
    console.log(" GET Profile ----- ", data);

    dispatch({ type: GET_PROFILE_SUCCESS, payload: data });
  } catch (error) {
    console.log("Error -----------", error);
    dispatch({ type: GET_PROFILE_FAILURE, payload: error });
  }
};

// Redux action (updateUserProfileAction)
export const updateUserProfileAction = (reqData) => async (dispatch) => {
  dispatch({ type: UPDATE_PROFILE_REQUEST });

  try {
    const { data } = await api.put(
      `${API_BASE_URL}/private/user/update`,
      reqData
    ); // API request

    // Dispatch success action if API responds
    dispatch({ type: UPDATE_PROFILE_SUCCESS, payload: data });
    return data; // If everything goes well, return data to be used in the component
  } catch (error) {
    // Dispatch failure action in case of error
    dispatch({ type: UPDATE_PROFILE_FAILURE, payload: error });

    // If the error is related to network issues, we throw it to be caught in the component
    throw error; // Propagate error to be caught in the component
  }
};

export const searchUserAction = (query) => async (dispatch) => {
  dispatch({ type: SEARCH_USER_REQUEST });

  try {
    const { data } = await api.get(`/private/users/search?query=${query}`);
    console.log(" Searched User ----- ", data);

    dispatch({ type: SEARCH_USER_SUCCESS, payload: data });
  } catch (error) {
    console.log("Error in Searching user -----------", error);
    dispatch({ type: SEARCH_USER_FAILURE, payload: error });
  }
};
