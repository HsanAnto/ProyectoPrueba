import { useEffect, useState } from "react";
import api from "../api/api";
import SectionCard from "./SectionCard";

function OrdenesTrabajoSection() {
    const [ordenes, setOrdenes] = useState([]);
    const [vehiculos, setVehiculos] = useState([]);
    const [tiposOt, setTiposOt] = useState([]);
    const [error, setError] = useState("");

    const [vehicleId, setVehicleId] = useState("");
    const [tipoOtId, setTipoOtId] = useState("");
    const [status, setStatus] = useState("");
    const [descripcion, setDescripcion] = useState("");
    const [createdBy, setCreatedBy] = useState("");

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [ordenesRes, vehiculosRes, tiposOtRes] = await Promise.all([
                    api.get("/ordenes"),
                    api.get("/vehiculos"),
                    api.get("/tipos-ot")
                ]);

                setOrdenes(ordenesRes.data);
                setVehiculos(vehiculosRes.data);
                setTiposOt(tiposOtRes.data);
            } catch (err) {
                setError("No se pudieron cargar las órdenes de trabajo");
                console.error(err);
            }
        };

        fetchData();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        const newOrden = {
            vehicleId: Number(vehicleId),
            tipoOtId: Number(tipoOtId),
            status,
            descripcion,
            createdBy
        };

        try {
            const response = await api.post("/ordenes", newOrden);
            setOrdenes((prev) => [...prev, response.data]);
            setVehicleId("");
            setTipoOtId("");
            setStatus("");
            setDescripcion("");
            setCreatedBy("");
            setError("");
        } catch (err) {
            setError("No se pudo crear la orden de trabajo");
            console.error(err);
        }
    };

    return (
        <SectionCard title="Órdenes de trabajo">
            <form className="simple-form" onSubmit={handleSubmit}>
                <select value={vehicleId} onChange={(e) => setVehicleId(e.target.value)}>
                    <option value="">Selecciona vehículo</option>
                    {vehiculos.map((vehiculo) => (
                        <option key={vehiculo.id} value={vehiculo.id}>
                            {vehiculo.placa}
                        </option>
                    ))}
                </select>

                <select value={tipoOtId} onChange={(e) => setTipoOtId(e.target.value)}>
                    <option value="">Selecciona tipo OT</option>
                    {tiposOt.map((tipo) => (
                        <option key={tipo.id} value={tipo.id}>
                            {tipo.nombre}
                        </option>
                    ))}
                </select>

                <input
                    type="text"
                    placeholder="Status"
                    value={status}
                    onChange={(e) => setStatus(e.target.value)}
                />

                <textarea
                    placeholder="Descripción"
                    value={descripcion}
                    onChange={(e) => setDescripcion(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="Creado por"
                    value={createdBy}
                    onChange={(e) => setCreatedBy(e.target.value)}
                />

                <button type="submit">Crear orden</button>
            </form>

            {error && <p className="error-message">{error}</p>}

            <div className="simple-list">
                {ordenes.map((orden) => (
                    <div className="simple-item" key={orden.id}>
                        <strong>Orden #{orden.id}</strong>
                        <div>Vehículo: {orden.vehicle_id?.placa || "Sin vehículo"}</div>
                        <div>Tipo OT: {orden.tipo_ot_id?.nombre || "Sin tipo OT"}</div>
                        <div>
                            Status: <span className="badge">{orden.status}</span>
                        </div>
                        <div>Descripción: {orden.descripcion || "Sin descripción"}</div>
                        <div>Creado por: {orden.created_by || "Sin creador"}</div>
                    </div>
                ))}
            </div>
        </SectionCard>
    );
}

export default OrdenesTrabajoSection;