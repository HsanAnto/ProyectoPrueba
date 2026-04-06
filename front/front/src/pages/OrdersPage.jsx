import { useEffect, useState } from "react";
import api from "../api/api";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from "@mui/material";

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
        <div>
            {error ? error : orders.map(orden => (
                <div key={orden.id}>
                    <p >Id Orden: {orden.id} </p>
                    <p >Vehiculo: {orden.tipo_ot_id} </p>
                    <p >Status: {orden.status} </p>
                </div>
                
            )
            ) }
        </div>
    )

}

export default OrdersPage;