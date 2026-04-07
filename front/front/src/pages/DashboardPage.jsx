import VehiculosSection from "../components/VehiculosSection";
import TiposOtSection from "../components/TiposOtSection";
import OrdenesTrabajoSection from "../components/OrdenesTrabajoSection";
import OrdenesRepuestosSection from "../components/OrdenesRepuestosSection";

function DashboardPage() {
  return (
    <div className="dashboard-page">
      <h1 className="dashboard-title">Panel de Taller</h1>
      <p className="dashboard-subtitle">Gestión de vehículos, tipos OT, órdenes y repuestos</p>

      <div className="dashboard-grid">
        <VehiculosSection />
        <TiposOtSection />
        <OrdenesTrabajoSection />
        <OrdenesRepuestosSection />
      </div>
    </div>
  );
}

export default DashboardPage;