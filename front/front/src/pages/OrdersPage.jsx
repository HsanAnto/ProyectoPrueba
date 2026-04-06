import { useEffect, useState } from "react";
import api from "../api/api";

function OrdersPage(){

    const [orders, setOrders] = useState([]);
    const [error, setError] = useState("");

    useEffect(() =>
    {
      const fetchMessage = async () => {
            try {
                const response = await api.get("/ordenes");
                setOrders(response.data);
            } catch (err) {
                setError("no se puedo conectar con el back");
                console.error(err);
            }
        };

        fetchMessage();
    },[]);

    return(
        <div></div>
    )




}

export default OrdersPage;