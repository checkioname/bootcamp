package repository

import (
	"app/internal"
	"errors"
	"fmt"
)

// NewVehicleMap is a function that returns a new instance of VehicleMap
func NewVehicleMap(db map[int]internal.Vehicle) *VehicleMap {
	// default db
	defaultDb := make(map[int]internal.Vehicle)
	if db != nil {
		defaultDb = db
	}
	return &VehicleMap{db: defaultDb}
}

// VehicleMap is a struct that represents a vehicle repository
type VehicleMap struct {
	// db is a map of vehicles
	db map[int]internal.Vehicle
}

// FindAll is a method that returns a map of all vehicles
func (r *VehicleMap) FindAll() (v map[int]internal.Vehicle, err error) {
	v = make(map[int]internal.Vehicle)

	for _, value := range r.db {
		v[value.Id] = value
	}

	return
}

func (r *VehicleMap) AddVehicle(v internal.Vehicle) error {
	if u, ok := r.db[v.Id]; ok {
		fmt.Printf("%q is the username of %q\n", u, v)
		return errors.New("vehicle already exists")
	}

	r.db[v.Id] = v
	return nil
}
