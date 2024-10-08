import {useEffect, useState} from "react";
import axios from "axios";
import './List.css'
import App from "../App.tsx";
import Create from "./Create.tsx";

type Props = {
    name: string;
}

export default function List(props : Props) {
    const [data, setData] = useState<DataType[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [showList, setShowList] = useState<boolean>(true);
    const [showPopUp, setShowPopUp] = useState<boolean>(false);
    const [search, setSearch] = useState<string>("");
    const image : string = `/src/assets/${props.name.toLowerCase()}.png`;
    // const createBg = document.getElementById('create');

    const getData = async () => {
        setLoading(true);
        try {
            const response = await axios.get<DataType[]>("/api/" + props.name.toLowerCase());
            console.log(response.data);
            setData(response.data);
        } catch (err) {
            if (axios.isAxiosError(err)) {
                console.error(err.message);
            } else {
                console.error("An unexpected error occurred:", err);
            }
        } finally {
            setLoading(false);
        }
    }

    const getFilterData = async () => {
        setLoading(true);
        try {
            const response = await axios.get<DataType[]>("/api/" + props.name.toLowerCase() + "/" + search.toLowerCase());
            console.log(response.data);
            // setData(response.data);
        } catch (err) {
            console.error("An unexpected error occurred:", err);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        getData();
    }, []);

    // useEffect(() => {
    //     if (search !== "") {
    //         getFilterData();
    //     } else {
    //         getData();
    //     }
    // }, [search]);

    if (loading) return <p>Loading...</p>;

    const handleDelete = async (id: number) => {
        try {
            await axios.delete(`api/${props.name.toLowerCase()}/${id}`);
            getData();
        } catch (err) {
            if (axios.isAxiosError(err)) {
                console.error(err.message);
            } else {
                console.error("An unexpected error occurred:", err);
            }
        }
    }

    const handleBack = () => {
        setShowList(false);
    }

    const handleCreate = () => {
        setShowPopUp(true);
        // createBg?.classList.add('active');
    }

    const handleCloseModal = () => {
        setShowPopUp(false);
        getData();
    };

    const handleSearch = () => {
        // setData([]);
        getFilterData();

    }

    const renderItem = (item : DataType, index : number) =>  {
        return (
            <div key={item.id} className="card">
                <div className="card-img">
                    <img className={item.image ? "image" : "image2"} src={item.image ? item.image : image}
                         alt={"image"}/>
                </div>
                <div className="card-info">
                    <div key={index} style={{color: "white"}}>
                        {Object.keys(item).map((key, keyIndex) => (
                            <div key={keyIndex}
                                 style={{display: "flex", justifyContent: "space-between", alignItems: "center"}}>
                                <p className="text-title">{key}:</p>
                                <p className="text-body">{item[key]}</p>
                            </div>
                        ))}
                    </div>
                </div>
                <div className="card-footer">
                    <span className="text-title">${item.price}</span>
                    <div style={{display: "flex", justifyContent: "space-between", alignItems: "center", width: "39%"}}>
                        <button onClick={() => handleDelete(item.id)} className={"delete"}>
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                fill="none"
                                viewBox="0 -30 69 10"
                                className="svgIcon bin-top"
                            >
                                <g clipPath="url(#clip0_35_24)">
                                    <path
                                        fill="black"
                                        d="M20.8232 2.62734L19.9948 4.21304C19.8224 4.54309 19.4808 4.75 19.1085 4.75H4.92857C2.20246 4.75 0 6.87266 0 9.5C0 12.1273 2.20246 14.25 4.92857 14.25H64.0714C66.7975 14.25 69 12.1273 69 9.5C69 6.87266 66.7975 4.75 64.0714 4.75H49.8915C49.5192 4.75 49.1776 4.54309 49.0052 4.21305L48.1768 2.62734C47.3451 1.00938 45.6355 0 43.7719 0H25.2281C23.3645 0 21.6549 1.00938 20.8232 2.62734ZM64.0023 20.0648C64.0397 19.4882 63.5822 19 63.0044 19H5.99556C5.4178 19 4.96025 19.4882 4.99766 20.0648L8.19375 69.3203C8.44018 73.0758 11.6746 76 15.5712 76H53.4288C57.3254 76 60.5598 73.0758 60.8062 69.3203L64.0023 20.0648Z"
                                    ></path>
                                </g>
                                <defs>
                                    <clipPath id="clip0_35_24">
                                        <rect fill="white" height="14" width="69"></rect>
                                    </clipPath>
                                </defs>
                            </svg>
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                fill="none"
                                viewBox="0 0 69 57"
                                className="svgIcon bin-bottom"
                            >
                                <g clipPath="url(#clip0_35_22)">
                                    <path
                                        fill="black"
                                        d="M20.8232 -16.3727L19.9948 -14.787C19.8224 -14.4569 19.4808 -14.25 19.1085 -14.25H4.92857C2.20246 -14.25 0 -12.1273 0 -9.5C0 -6.8727 2.20246 -4.75 4.92857 -4.75H64.0714C66.7975 -4.75 69 -6.8727 69 -9.5C69 -12.1273 66.7975 -14.25 64.0714 -14.25H49.8915C49.5192 -14.25 49.1776 -14.4569 49.0052 -14.787L48.1768 -16.3727C47.3451 -17.9906 45.6355 -19 43.7719 -19H25.2281C23.3645 -19 21.6549 -17.9906 20.8232 -16.3727ZM64.0023 1.0648C64.0397 0.4882 63.5822 0 63.0044 0H5.99556C5.4178 0 4.96025 0.4882 4.99766 1.0648L8.19375 50.3203C8.44018 54.0758 11.6746 57 15.5712 57H53.4288C57.3254 57 60.5598 54.0758 60.8062 50.3203L64.0023 1.0648Z"
                                    ></path>
                                </g>
                                <defs>
                                    <clipPath id="clip0_35_22">
                                        <rect fill="white" height="57" width="69"></rect>
                                    </clipPath>
                                </defs>
                            </svg>
                        </button>
                        <button className="editBtn">
                            <svg height="1em" viewBox="0 0 512 512">
                                <path
                                    d="M410.3 231l11.3-11.3-33.9-33.9-62.1-62.1L291.7 89.8l-11.3 11.3-22.6 22.6L58.6 322.9c-10.4 10.4-18 23.3-22.2 37.4L1 480.7c-2.5 8.4-.2 17.5 6.1 23.7s15.3 8.5 23.7 6.1l120.3-35.4c14.1-4.2 27-11.8 37.4-22.2L387.7 253.7 410.3 231zM160 399.4l-9.1 22.7c-4 3.1-8.5 5.4-13.3 6.9L59.4 452l23-78.1c1.4-4.9 3.8-9.4 6.9-13.3l22.7-9.1v32c0 8.8 7.2 16 16 16h32zM362.7 18.7L348.3 33.2 325.7 55.8 314.3 67.1l33.9 33.9 62.1 62.1 33.9 33.9 11.3-11.3 22.6-22.6 14.5-14.5c25-25 25-65.5 0-90.5L453.3 18.7c-25-25-65.5-25-90.5 0zm-47.4 168l-144 144c-6.2 6.2-16.4 6.2-22.6 0s-6.2-16.4 0-22.6l144-144c6.2-6.2 16.4-6.2 22.6 0s6.2 16.4 0 22.6z"
                                ></path>
                            </svg>
                        </button>
                    </div>
                </div>
            </div>
        );
    }

    return (
        <>
            {showList ?
                <>
                    <div className={"container-list"}>
                        <h1>{props.name}s List</h1>
                        <div className="search-bar">
                            <div className="InputContainer">
                                <input
                                    onChange={(e) => {
                                        console.log(e.currentTarget.value);
                                        setSearch(e.target.value);
                                    }}
                                    value={search}
                                    placeholder="Search"
                                    id="input"
                                    className="input"
                                    name="text"
                                    type="text"
                                />

                                <label onClick={handleSearch} className="labelforsearch" htmlFor="input">
                                    <svg className="searchIcon" viewBox="0 0 512 512">
                                        <path
                                            d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z"
                                        ></path>
                                    </svg>
                                </label>
                            </div>

                        </div>
                        <div className={"container-card"}>
                            {search == "" ? data.map((item, index) => (
                                renderItem(item,index)
                            )) : data.filter(itemSearch => itemSearch.id.toString().includes(search)).map((item, index) => (
                                renderItem(item, index)
                            ))}
                            <div onClick={handleCreate} className={"add-card"}><b>+</b></div>
                        </div>
                        <button onClick={handleBack} className={"backButton"}>Go Back</button>
                    </div>
                    <div>
                        {showPopUp ?
                            <Create name={props.name} onClose={handleCloseModal} isActive={showPopUp}></Create> : ""}
                    </div>
                </>
                : <App/>}
        </>
    )
}
