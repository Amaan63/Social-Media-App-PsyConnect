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
  UPDATE_PROFILE_REQUEST,
  UPDATE_PROFILE_SUCCESS,
  UPDATE_PROFILE_FAILURE,
} from "./authentication.actionType";

const initialState = {
  jwt: null,
  error: null,
  loading: false,
  user: null,
};

export const authenticationReducer = (state = initialState, action) => {
  // 📌 Reducer function takes current state and an action, returns new state

  switch (action.type) {
    // 🔄 Decide what to do based on action type

    case LOGIN_REQUEST:
    case REGISTER_REQUEST:
    case GET_PROFILE_REQUEST:
    case UPDATE_PROFILE_REQUEST: // 🟡 Profile update started
      return { ...state, loading: true, error: null };
    // 🚀 Login started: set loading true, clear previous errors

    case GET_PROFILE_SUCCESS:
      return { ...state, user: action.payload, error: null, loading: false };

    case UPDATE_PROFILE_SUCCESS: // ✅ Profile updated: update user in state
      return { ...state, user: action.payload, error: null, loading: false };

    case LOGIN_SUCCESS:
    case REGISTER_SUCCESS:
      return { ...state, jwt: action.payload, loading: false, error: null };
    // ✅ Login successful: save token (jwt), stop loading, clear error

    case LOGIN_FAILURE:
    case REGISTER_FAILURE:
    case GET_PROFILE_FAILURE:
    case UPDATE_PROFILE_FAILURE: // ❌ Profile update failed
      return { ...state, loading: false, error: action.payload };
    // ❌ Login failed: stop loading, store error message

    default:
      return state;
    // ⚙️ No matching action: return current state unchanged
  }
};
