import { useEffect, useState } from "react";
import api from "../api/api";
import SectionCard from "./SectionCard";

function OrdenesRepuestosSection() {
    const [repuestos, setRepuestos] = useState([]);
    const [ordenes, setOrdenes] = useState([]);
    const [error, setError] = useState("");

    const [ordenTrabajoId, setOrdenTrabajoId] = useState("");
    const [nombreRepuesto, setNombreRepuesto] = useState("");
    const [cantidad, setCantidad] = useState("");
    const [costoUnitario, setCostoUnitario] = useState("");

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [repuestosRes, ordenesRes] = await Promise.all([
                api.get("/ordenes-repuestos"),
                api.get("/ordenes")
                ]);

                setRepuestos(repuestosRes.data);
                setOrdenes(ordenesRes.data);
            } catch (err) {
                setError("No se pudieron cargar los repuestos");
                console.error(err);
            }
        };

        fetchData();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        const newRepuesto = {
            orden_trabajo_id: Number(ordenTrabajoId),
            nombre_repuesto: nombreRepuesto,
            cantidad: Number(cantidad),
            costo_unitario: Number(costoUnitario)
        };

        try {
            const response = await api.post("/ordenes-repuestos", newRepuesto);
            setRepuestos((prev) => [...prev, response.data]);

            setOrdenTrabajoId("");
            setNombreRepuesto("");
            setCantidad("");
            setCostoUnitario("");
            setError("");
        } catch (err) {
            setError("No se pudo crear la orden de repuesto");
            console.error(err);
        }
    };

    return (
        <SectionCard title="Órdenes de repuestos">
            <form className="simple-form" onSubmit={handleSubmit}>
                <select value={ordenTrabajoId} onChange={(e) => setOrdenTrabajoId(e.target.value)}>
                    <option value="">Selecciona orden de trabajo</option>
                    {ordenes.map((orden) => (
                        <option key={orden.id} value={orden.id}>
                            Orden #{orden.id}
                        </option>
                    ))}
                </select>

                <input
                    type="text"
                    placeholder="Nombre del repuesto"
                    value={nombreRepuesto}
                    onChange={(e) => setNombreRepuesto(e.target.value)}
                />

                <input
                    type="number"
                    placeholder="Cantidad"
                    value={cantidad}
                    onChange={(e) => setCantidad(e.target.value)}
                />

                <input
                    type="number"
                    step="0.01"
                    placeholder="Costo unitario"
                    value={costoUnitario}
                    onChange={(e) => setCostoUnitario(e.target.value)}
                />

                <button type="submit">Crear repuesto</button>
            </form>

            {error && <p className="error-message">{error}</p>}

            <div className="simple-list">
                {repuestos.map((repuesto) => (
                    <div className="simple-item" key={repuesto.id}>
                        <strong>{repuesto.nombre_repuesto}</strong>
                        <div>Orden trabajo: {repuesto.orden_trabajo_id?.id || "Sin orden"}</div>
                        <div>Cantidad: {repuesto.cantidad}</div>
                        <div>Costo unitario: ${repuesto.costo_unitario}</div>
                    </div>
                ))}
            </div>
        </SectionCard>
    );
}

export default OrdenesRepuestosSection;