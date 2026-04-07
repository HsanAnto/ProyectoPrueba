import { useEffect, useState } from "react";
import api from "../api/api";
import SectionCard from "./SectionCard";

function VehiculosSection() {
    const [vehiculos, setVehiculos] = useState([]);
    const [error, setError] = useState("");

    const [placa, setPlaca] = useState("");
    const [marca, setMarca] = useState("");
    const [modelo, setModelo] = useState("");
    const [anio, setAnio] = useState("");
    const [isActive, setIsActive] = useState("true");

    useEffect(() => {
        const fetchVehiculos = async () => {
            try {
                const response = await api.get("/vehiculos");
                setVehiculos(response.data);
            } catch (err) {
                setError("No se pudieron cargar los vehículos");
                console.error(err);
            }
        };

        fetchVehiculos();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        const newVehiculo = {
            placa,
            marca,
            modelo,
            anio: anio ? Number(anio) : null,
            is_active: isActive === "true"
        };

        try {
            const response = await api.post("/vehiculos", newVehiculo);
            setVehiculos((prev) => [...prev, response.data]);

            setPlaca("");
            setMarca("");
            setModelo("");
            setAnio("");
            setIsActive("true");
            setError("");
        } catch (err) {
            setError("No se pudo crear el vehículo");
            console.error(err);
        }
    };

    return (
        <SectionCard title="Vehículos">
            <form className="simple-form" onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Placa"
                    value={placa}
                    onChange={(e) => setPlaca(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="Marca"
                    value={marca}
                    onChange={(e) => setMarca(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="Modelo"
                    value={modelo}
                    onChange={(e) => setModelo(e.target.value)}
                />

                <input
                    type="number"
                    placeholder="Año"
                    value={anio}
                    onChange={(e) => setAnio(e.target.value)}
                />

                <select value={isActive} onChange={(e) => setIsActive(e.target.value)}>
                    <option value="true">Activo</option>
                    <option value="false">Inactivo</option>
                </select>

                <button type="submit">Crear vehículo</button>
            </form>

            {error && <p className="error-message">{error}</p>}

            <div className="simple-list">
                {vehiculos.map((vehiculo) => (
                    <div className="simple-item" key={vehiculo.id}>
                        <strong>{vehiculo.placa}</strong>
                        <div>Marca: {vehiculo.marca}</div>
                        <div>Modelo: {vehiculo.modelo || "Sin modelo"}</div>
                        <div>Año: {vehiculo.anio || "Sin año"}</div>
                        <div>
                            Estado:{" "}
                            <span className="badge">
                                {vehiculo.is_active ? "Activo" : "Inactivo"}
                            </span>
                        </div>
                    </div>
                ))}
            </div>
        </SectionCard>
    );
}

export default VehiculosSection;