package com.mkraskiewicz.controllers.v1;

import com.mkraskiewicz.api.v1.model.VendorDTO;
import com.mkraskiewicz.api.v1.model.VendorListDTO;
import com.mkraskiewicz.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Maciej on 09/07/2018
 */
@Api(description = "Controller responsible for Vendors.")
@Controller
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "Returns back list of all vendors.", notes = "It is not limited by anything. (TODO)")
    @GetMapping
    public ResponseEntity<VendorListDTO> getAllVendors(){

        return new ResponseEntity<VendorListDTO>(new VendorListDTO(vendorService.getAllVendors()),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Returns Vendor saved by ID provided in path.")
    @GetMapping("{id}")
    public ResponseEntity<VendorDTO> getVendorById(@PathVariable("id") Long id){

        return new ResponseEntity<VendorDTO>(vendorService.getVendorById(id),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a new Vendor.")
    @PostMapping
    public ResponseEntity<VendorDTO> createNewVendor(@RequestBody VendorDTO vendorDTO){

        return new ResponseEntity<VendorDTO>(vendorService.createNewVendor(vendorDTO),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Updates existing Vendor.")
    @PutMapping("{id}")
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable("id") Long id,
                                                  @RequestBody VendorDTO vendorDTO){

        return new ResponseEntity<VendorDTO>(vendorService.saveVendorByDTO(id, vendorDTO),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Updates a Vendor property.")
    @PatchMapping("{id}")
    public ResponseEntity<VendorDTO> patchVendor(@PathVariable("id") Long id,
                                                 @RequestBody VendorDTO vendorDto){

        return new ResponseEntity<VendorDTO>(vendorService.patchVendor(id, vendorDto),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a Vendor.")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable("id") Long id){

        vendorService.deleteVendorById(id);
        return  new ResponseEntity<Void>(HttpStatus.OK);
    }
}
