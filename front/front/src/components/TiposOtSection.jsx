import { useEffect, useState } from "react";
import api from "../api/api";
import SectionCard from "./SectionCard";

function TiposOtSection() {
    const [tiposOt, setTiposOt] = useState([]);
    const [error, setError] = useState("");
    const [nombre, setNombre] = useState("");
    const [isActive, setIsActive] = useState("true");

    useEffect(() => {
        const fetchTiposOt = async () => {
            try {
                const response = await api.get("/tipos-ot");
                setTiposOt(response.data);
            } catch (err) {
                setError("No se pudieron cargar los tipos OT");
                console.error(err);
            }
        };

        fetchTiposOt();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        const newTipoOt = {
            nombre,
            is_active: isActive === "true"
        };

        try {
            const response = await api.post("/tipos-ot", newTipoOt);
            setTiposOt((prev) => [...prev, response.data]);

            setNombre("");
            setIsActive("true");
            setError("");
        } catch (err) {
            setError("No se pudo crear el tipo OT");
            console.error(err);
        }
    };

    return (
        <SectionCard title="Tipos OT">
            <form className="simple-form" onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Nombre exacto del enum"
                    value={nombre}
                    onChange={(e) => setNombre(e.target.value)}
                />

                <select value={isActive} onChange={(e) => setIsActive(e.target.value)}>
                    <option value="true">Activo</option>
                    <option value="false">Inactivo</option>
                </select>

                <button type="submit">Crear tipo OT</button>
            </form>

            {error && <p className="error-message">{error}</p>}

            <div className="simple-list">
                {tiposOt.map((tipo) => (
                    <div className="simple-item" key={tipo.id}>
                        <strong>{tipo.nombre}</strong>
                        <div>
                            Estado:{" "}
                            <span className="badge">
                                {tipo.is_active ? "Activo" : "Inactivo"}
                            </span>
                        </div>
                    </div>
                ))}
            </div>
        </SectionCard>
    );
}

export default TiposOtSection;