const cloud_name = "fantasy-hub";
const upload_preset = "Fantasy Hub";

export const uploadToCloudinary = async (file, fileType) => {
  if (file && fileType) {
    const data = new FormData();
    data.append("file", file);
    data.append("upload_preset", upload_preset);
    data.append("cloud_name", cloud_name);

    const res = await fetch(
      `https://api.cloudinary.com/v1_1/${cloud_name}/${fileType}/upload`,
      { method: "post", body: data }
    );
    console.log("res-----", res);
    const fileData = await res.json();
    console.log("Url------", fileData.url);
    return fileData.url;
  } else {
    console.log("Error...........");
  }
};
