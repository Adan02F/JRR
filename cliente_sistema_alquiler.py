import requests
from datetime import date

BASE_URL = "http://localhost:8080"

# =============================
# CLIENTES
# =============================
def listar_clientes():
    r = requests.get(f"{BASE_URL}/clientes")
    if r.status_code == 200:
        clientes = r.json()
        print("\n--- Lista de Clientes ---")
        for c in clientes:
            print(f"ID: {c['id']} | {c['nombres']} {c['apellidos']} | Cédula: {c['cedula']}")
    else:
        print("Error al obtener clientes:", r.status_code, r.text)


def crear_cliente():
    print("\n--- Registrar Cliente ---")
    nuevo = {
        "nombres": input("Nombres: "),
        "apellidos": input("Apellidos: "),
        "direccion": input("Direccion: "),
        "celular": input("Celular: "),
        "cedula": int(input("Cedula: "))
    }
    r = requests.post(f"{BASE_URL}/clientes", json=nuevo)
    if r.status_code in (200, 201):
        print("Cliente registrado:", r.json())
    else:
        print("Error al registrar cliente:", r.status_code, r.text)


# =============================
# VEHICULOS
# =============================
def listar_vehiculos():
    r = requests.get(f"{BASE_URL}/vehiculos")
    if r.status_code == 200:
        vehiculos = r.json()
        print("\n--- Lista de Vehiculos ---")
        for v in vehiculos:
            print(f"ID: {v['id']} | {v['marca']} {v['modelo']} | Placa: {v['placa']}")
    else:
        print("Error al obtener vehiculos:", r.status_code, r.text)


def crear_vehiculo():
    print("\n--- Registrar Vehiculo ---")
    nuevo = {
        "placa": input("Placa: "),
        "marca": input("Marca: "),
        "modelo": input("Modelo: "),
        "anio": int(input("Anio: ")),
        "color": input("Color: "),
        "estado": input("Estado: ")
    }
    r = requests.post(f"{BASE_URL}/vehiculos", json=nuevo)
    if r.status_code in (200, 201):
        print("Vehiculo registrado:", r.json())
    else:
        print("Error al registrar vehiculo:", r.status_code, r.text)


def eliminar_vehiculo():
    idv = input("ID del vehiculo a eliminar: ")
    r = requests.delete(f"{BASE_URL}/vehiculos/{idv}")
    if r.status_code in (200, 204):
        print("Vehiculo eliminado correctamente.")
    else:
        print("Error al eliminar vehiculo:", r.status_code, r.text)


# =============================
# RESERVAS
# =============================
def listar_reservas():
    r = requests.get(f"{BASE_URL}/reservas")
    if r.status_code == 200:
        reservas = r.json()
        print("\n--- Lista de Reservas ---")
        for rsv in reservas:
            print(f"ID: {rsv['id']} | Estado: {rsv['estado']} | FechaInicio: {rsv['fechaInicio']} | FechaFin: {rsv['fechaFin']}")
    else:
        print("Error al obtener reservas:", r.status_code, r.text)


def crear_reserva():
    print("\n--- Crear Reserva ---")
    reserva = {
        "fechaInicio": input("Fecha de inicio (YYYY-MM-DD): "),
        "fechaFin": input("Fecha de fin (YYYY-MM-DD): "),
        "estado": input("Estado: "),
        "vehiculo": {"id": int(input("ID del vehiculo: "))},
        "cliente": {"id": int(input("ID del cliente: "))}
    }
    r = requests.post(f"{BASE_URL}/reservas", json=reserva)
    if r.status_code in (200, 201):
        print("Reserva creada:", r.json())
    else:
        print("Error al crear reserva:", r.status_code, r.text)


def listar_reservas():
    r = requests.get(f"{BASE_URL}/reservas")
    if r.status_code == 200:
        reservas = r.json()
        print("\n--- Lista de Reservas ---")
        for rsv in reservas:
            cliente_id = rsv.get('cliente', {}).get('id', 'N/A')
            vehiculo_id = rsv.get('vehiculo', {}).get('id', 'N/A')
            print(f"ID: {rsv['id']} | Estado: {rsv['estado']} | "
                  f"FechaInicio: {rsv['fechaInicio']} | FechaFin: {rsv['fechaFin']} | "
                  f"Cliente ID: {cliente_id} | Vehiculo ID: {vehiculo_id}")
    else:
        print("Error al obtener reservas:", r.status_code, r.text)


def buscar_reservas_por_cliente():
    id_cliente = input("ID del cliente: ")
    r = requests.get(f"{BASE_URL}/reservas/cliente/{id_cliente}")
    if r.status_code == 200:
        reservas = r.json()
        print(f"\n--- Reservas del Cliente {id_cliente} ---")
        for rsv in reservas:
            vehiculo_id = rsv.get('vehiculo', {}).get('id', 'N/A')
            print(f"ID: {rsv['id']} | Estado: {rsv.get('estado', 'N/A')} | "
                  f"Fecha inicio: {rsv.get('fechaInicio')} | Fecha fin: {rsv.get('fechaFin')} | "
                  f"Vehiculo ID: {vehiculo_id}")
    else:
        print("Error:", r.status_code, r.text)


def buscar_reservas_por_vehiculo():
    id_vehiculo = input("ID del vehiculo: ")
    r = requests.get(f"{BASE_URL}/reservas/vehiculo/{id_vehiculo}")
    if r.status_code == 200:
        reservas = r.json()
        print(f"\n--- Reservas del Vehiculo {id_vehiculo} ---")
        for rsv in reservas:
            cliente_id = rsv.get('cliente', {}).get('id', 'N/A')
            print(f"ID: {rsv['id']} | Estado: {rsv['estado']} | Cliente ID: {cliente_id}")
    else:
        print("Error:", r.status_code, r.text)



# =============================
# MENU PRINCIPAL
# =============================
def menu():
    while True:
        print("""
======== SISTEMA DE ALQUILER (Cliente Python) ========
1. Listar clientes
2. Crear cliente
3. Listar vehiculos
4. Crear vehiculo
5. Eliminar vehiculo
6. Listar reservas
7. Crear reserva
8. Buscar reservas por cliente
9. Buscar reservas por vehiculo
10. Buscar reservas por fecha
0. Salir
""")
        op = input("Seleccione una opcion: ")
        if op == "1":
            listar_clientes()
        elif op == "2":
            crear_cliente()
        elif op == "3":
            listar_vehiculos()
        elif op == "4":
            crear_vehiculo()
        elif op == "5":
            eliminar_vehiculo()
        elif op == "6":
            listar_reservas()
        elif op == "7":
            crear_reserva()
        elif op == "8":
            buscar_reservas_por_cliente()
        elif op == "9":
            buscar_reservas_por_vehiculo()
        elif op == "10":
            buscar_reservas_por_fecha()
        elif op == "0":
            print("Saliendo...")
            break
        else:
            print("Opcion invalida.")


if __name__ == "__main__":
    menu()
