package com.example.ProyectoCoder.services.impl;

import com.example.ProyectoCoder.models.ClientEntity;
import com.example.ProyectoCoder.models.ProductoEntity;
import com.example.ProyectoCoder.models.ProductoVenta;
import com.example.ProyectoCoder.models.VentasEntity;
import com.example.ProyectoCoder.models.dto.ProductoDto;
import com.example.ProyectoCoder.models.dto.ProductoRequest;
import com.example.ProyectoCoder.models.dto.VentaDto;
import com.example.ProyectoCoder.models.dto.VentasRequest;
import com.example.ProyectoCoder.repository.ProductoVentaRepository;
import com.example.ProyectoCoder.repository.VentasRepository;
import com.example.ProyectoCoder.services.ClienteService;
import com.example.ProyectoCoder.services.ProductoService;
import com.example.ProyectoCoder.services.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VentasServiceImp implements VentasService {

    @Autowired
    private VentasRepository ventasRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoVentaRepository productoVentaRepository;


    @Override
    public Object realizarVenta(VentasRequest venta) {
        VentasEntity ticket = new VentasEntity();
        Optional<ClientEntity> client = clienteService.buscarPorId(venta.getIdClient());
        List<ProductoEntity> listaProduct = new ArrayList<>();
        List<ProductoDto> productDtos = new ArrayList<>();
        double total = 0.0;

        if(client.isPresent()){
            ticket.setClient(client.get());
            for(ProductoRequest productRequest : venta.getProducts()){
                Optional<ProductoEntity> product = productoService.buscarPorId(productRequest.getIdProduct());
                if(product.isPresent()){
                    if(product.get().getStock()>=productRequest.getCount()){
                        ProductoDto productDto = new ProductoDto(product.get(), productRequest.getCount());
                        productDtos.add(productDto);
                        listaProduct.add(product.get());
                        total=total + product.get().getPrecio() * productRequest.getCount();
                    }
                }else{
                    return null;
                }
            }
            if(!listaProduct.isEmpty()) {

                for (int i = 0; i < listaProduct.size(); i++) {
                    List<ProductoEntity> list = new ArrayList<>(listaProduct);
                    ProductoEntity productEntity = list.get(i);
                    productEntity.setStock(productEntity.getStock() - venta.getProducts().get(i).getCount());
                    productoService.modificarStock(productEntity.getId(), productEntity.getStock());
                }
                ticket.setProducts(listaProduct);
                ticket.setFecha(new Date());
                ticket.setPrecioFinal(total);
                VentasEntity saleEntity = ventasRepository.save(ticket);

                return new VentaDto(saleEntity, productDtos);
            }
        }
        return null;
    }

    @Override
    public void modifySaleProduct(VentaDto ventaDto){
        Long sale = ventaDto.getId();
        for (ProductoDto productDto : ventaDto.getProductos()) {
            Long product = productDto.getId();
            ProductoVenta productSale = productoVentaRepository.findProductSale(sale, product);
            productSale.setCount(productDto.getCantidad());
            productoVentaRepository.save(productSale);
        }
    }

    @Override
    public Object ventas( ) {
        return ventasRepository.findAll();
    }


}
