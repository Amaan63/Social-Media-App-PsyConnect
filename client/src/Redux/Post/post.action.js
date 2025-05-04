import {
  CREATE_POST_FAILURE,
  CREATE_POST_REQUEST,
  CREATE_POST_SUCCESS,
} from "./post.actionType";
import { api, API_BASE_URL } from "../../config/api";

export const createPostAction = (postData) => async (dispatch) => {
  dispatch({ type: CREATE_POST_REQUEST });
  try {
    const { data } = await api.post(
      `${API_BASE_URL}/private/posts/createPost`,
      postData
    );
    dispatch({ type: CREATE_POST_SUCCESS, payload: data });
    console.log("Created Post ", data);
  } catch (error) {
    console.log("error ", error);
    dispatch({ type: CREATE_POST_FAILURE, payload: error });
  }
};
