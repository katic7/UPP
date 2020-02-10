import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IspravkaPdfComponent } from './ispravka-pdf.component';

describe('IspravkaPdfComponent', () => {
  let component: IspravkaPdfComponent;
  let fixture: ComponentFixture<IspravkaPdfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IspravkaPdfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IspravkaPdfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
